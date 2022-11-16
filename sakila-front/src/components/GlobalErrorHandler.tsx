import { ErrorBoundary } from 'react-error-boundary';
import FallbackComponent from './FallbackComponent';
import React from 'react';
import { useHistory } from 'react-router-dom';

export default function GlobalErrorHandler({ children }: { children: React.ReactNode }) {
  const history = useHistory();

  const handleOnError = (error: Error, info: { componentStack: string }) => {};

  return (
    <ErrorBoundary FallbackComponent={FallbackComponent} onError={handleOnError}>
      {children}
    </ErrorBoundary>
  );
}
